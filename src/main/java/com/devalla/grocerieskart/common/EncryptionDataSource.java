package com.devalla.grocerieskart.common;

import java.util.Enumeration;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.Name;
import javax.naming.RefAddr;
import javax.naming.Reference;
import javax.naming.StringRefAddr;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.apache.tomcat.jdbc.pool.DataSourceFactory;


public class EncryptionDataSource extends DataSourceFactory {
	
	private static Logger logger = Logger.getLogger(EncryptionDataSource.class);	
	private static String PASSWORD = "password";

	
	public Object getObjectInstance(Object obj, Name name, Context nameCtx, Hashtable<?, ?> environment) throws Exception {
		try {
			if ((obj == null) || (!(obj instanceof Reference)))
			      return null;
			
			Reference ref = (Reference)obj;
			if (ref != null) {
				int addrPos = getPasswordRefAddrPosition(ref, PASSWORD);
				RefAddr refAddr = ref.get(PASSWORD);
				if (refAddr != null) {
					String content = (String) refAddr.getContent();
					String passString = new String(Base64.decodeBase64(content.getBytes()));
					ref.remove(addrPos);
					ref.add(addrPos, new StringRefAddr(PASSWORD, passString));
				}
			}
		} catch (Exception e) {
			logger.error("Error while creating the DataSource", e.getCause());
			throw new Exception("Error while creating the DataSource");
		}
		
		return super.getObjectInstance(obj, name, nameCtx, environment);
	}

	private int getPasswordRefAddrPosition(Reference ref, String password) throws Exception {
		Enumeration<?> enumeration = ref.getAll();
		int addrPos = -1;
		int i = 0;
		while (enumeration.hasMoreElements()) {
			RefAddr refAddr = (RefAddr) enumeration.nextElement();
			i = i + 1;
			String refType = refAddr.getType();
			if (refType.equals(password)) {
				addrPos = i - 1;
				break;
			}
		}
		if (addrPos == -1) {
			throw new Exception("Did not find the name/value pair for AddrType " + password);
		}
		return addrPos;
	}
	
	

}
