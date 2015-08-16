package com.jwm.routercontroller.router;

import com.jwm.routercontroller.util.*;
import com.jwm.routercontroller.signal.*;

import java.nio.charset.*;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.util.Assert;
import org.apache.commons.codec.binary.Base64;

public class RouterAdapterHttpImpl implements RouterAdapter {

	private static Logger log = LogManager.getLogger(RouterAdapterHttpImpl.class);
	private String ipAddress, b64UserPass;
	public RouterAdapterHttpImpl(String ipAddress, String user, String password, IpAddressValidator ipValidator) {
		Assert.isTrue(ipValidator.validate(ipAddress));
		Assert.hasLength(user);
		Assert.hasLength(password);
		this.ipAddress = ipAddress;
		b64UserPass = new String(Base64.encodeBase64((user + ":" + password).getBytes()));
	}

	/**
	 * Send http post to enable ssh access
	 */
	@Override
	public void enableSshAccess() {
		try {
			log.info("enableSshAccess()");
			postToRouter(true);
		}
		catch(Exception ex) {
			log.error(ex.getMessage(), ex);
		}
	}

	/**
	 * Send http post to disable ssh access
	 */
	@Override
	public void disableSshAccess() {
		try {
			log.info("disableSshAccess()");
			postToRouter(false);
		}
		catch(Exception ex) {
			log.error(ex.getMessage(), ex);
		}
	}

	private void postToRouter(boolean enableSsh) {

		log.info("postToRouter(enableSsh:'"+enableSsh+"')");

		String url = "http://"+ipAddress+"/apply.cgi";
		URL obj = null;
		try {
			obj = new URL(url);
		}
		catch (MalformedURLException ex) {
			log.error(ex.getMessage(), ex);
		}

		HttpURLConnection con = null;
		try {
			con = (HttpURLConnection) obj.openConnection();
		}
		catch(IOException ex) {
			log.error(ex.getMessage(), ex);
		}

		String sshOnOff = enableSsh ? "on" : "off";
		String postData = "submit_button=SingleForward&action=Apply&forward_single=15&wait_time=3&name0=None&name1=None&name2=None&name3=None&name4=None&name5=web&name6=&name7=&name8=&name9=&name10=&name11=&name12=&name13=&name14=&name15=&name16=&name17=&name18=&name19=&ip0=0&ip1=0&ip2=0&ip3=0&ip4=0&from5=80&to5=80&pro5=both&ip5=139&from6=6789&to6=22&pro6=both&ip6=109&enable6="+sshOnOff+"&from7=0&to7=0&pro7=both&ip7=0&from8=0&to8=0&pro8=both&ip8=0&from9=0&to9=0&pro9=both&ip9=0&from10=0&to10=0&pro10=both&ip10=0&from11=0&to11=0&pro11=both&ip11=0&from12=0&to12=0&pro12=both&ip12=0&from13=0&to13=0&pro13=both&ip13=0&from14=0&to14=0&pro14=both&ip14=0&from15=0&to15=0&pro15=both&ip15=0&from16=0&to16=0&pro16=both&ip16=0&from17=0&to17=0&pro17=both&ip17=0&from18=0&to18=0&pro18=both&ip18=0&from19=0&to19=0&pro19=both&ip19=0";
		//String postData = "submit_button=SingleForward&action=Apply&forward_single=15&wait_time=3&name6=ssh&from6=6789&to6=22&pro6=both&ip6=109&enable6="+sshOnOff;
		byte[] postDataBytes = postData.getBytes(StandardCharsets.UTF_8);
		int contentLength = postDataBytes.length;

		try {
			con.setRequestMethod("POST");
			con.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
			con.setRequestProperty("Authorization", "Basic " + b64UserPass);
			con.setRequestProperty("Content-Length", Integer.toString(contentLength));
			con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			con.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.155 Safari/537.36");
			con.setDoOutput(true);
			//
			// Send post request
			StringBuffer response = new StringBuffer();
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			log.info("About to write postData:" + postData);	
			wr.write(postDataBytes);
			wr.flush();
			wr.close();

			try {
				log.info(con);
				int responseCode = con.getResponseCode();
				log.info("ResponseCode:"+responseCode);
			}
			catch(Exception ex1) {
				log.error(ex1.getMessage(), ex1);
			}

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
		}
		catch(Exception ex) { 
			log.error(ex.getMessage(), ex);
		}

	}
}
