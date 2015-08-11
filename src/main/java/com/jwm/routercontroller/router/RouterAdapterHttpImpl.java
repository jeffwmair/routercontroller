package com.jwm.routercontroller.router;

import com.jwm.routercontroller.util.*;
import com.jwm.routercontroller.signal.*;

import java.io.*;
import java.net.URL;
import java.net.HttpURLConnection;
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
			postToRouter(false);
		}
		catch(Exception ex) {
			log.error(ex.getMessage(), ex);
		}
	}

	private void postToRouter(boolean enableSsh) throws Exception {

		log.info("postToRouter(enableSsh:'"+enableSsh+"')");

		String url = "http://"+ipAddress+"/apply.cgi";
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		//add reuqest header
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", "Mozilla/5.0");
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		con.setRequestProperty("Authorization", "Basic " + b64UserPass);
		String sshOnOff = enableSsh ? "on" : "off";

		String postData = "submit_button=SingleForward&action=Apply&forward_single=15&wait_time=3&name0=None&name1=None&name2=None&name3=None&name4=None&name5=web&name6=&name7=&name8=&name9=&name10=&name11=&name12=&name13=&name14=&name15=&name16=&name17=&name18=&name19=&ip0=0&ip1=0&ip2=0&ip3=0&ip4=0&from5=80&to5=80&pro5=both&ip5=139&from6=6789&to6=22&pro6=both&ip6=109&enable6="+sshOnOff+"&from7=0&to7=0&pro7=both&ip7=0&from8=0&to8=0&pro8=both&ip8=0&from9=0&to9=0&pro9=both&ip9=0&from10=0&to10=0&pro10=both&ip10=0&from11=0&to11=0&pro11=both&ip11=0&from12=0&to12=0&pro12=both&ip12=0&from13=0&to13=0&pro13=both&ip13=0&from14=0&to14=0&pro14=both&ip14=0&from15=0&to15=0&pro15=both&ip15=0&from16=0&to16=0&pro16=both&ip16=0&from17=0&to17=0&pro17=both&ip17=0&from18=0&to18=0&pro18=both&ip18=0&from19=0&to19=0&pro19=both&ip19=0";

		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(postData);
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();
		log.info("Sending 'POST' request to URL : " + url);
		//log.info("Post parameters : " + postData);
		log.info("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		if (log.isTraceEnabled()) {
			log.trace(response.toString());
		}
	}
}
