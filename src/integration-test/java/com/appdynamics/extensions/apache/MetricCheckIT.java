package com.appdynamics.extensions.apache;

import com.appdynamics.extensions.http.UrlBuilder;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Satish Muddam
 */

public class MetricCheckIT {

    private static final String USER_AGENT = "Mozilla/5.0";

    private CloseableHttpClient httpClient;

    private static final ObjectMapper MAPPER = new ObjectMapper();


    @Before
    public void setup() {

        CredentialsProvider provider = new BasicCredentialsProvider();
        UsernamePasswordCredentials credentials
                = new UsernamePasswordCredentials("admin@customer1", "admin");
        provider.setCredentials(AuthScope.ANY, credentials);

        httpClient = HttpClientBuilder.create()
                .setDefaultCredentialsProvider(provider)
                .build();

    }

    @After
    public void tearDown() {
        try {
            httpClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testHearBeatMetric() throws IOException {


        UrlBuilder builder = UrlBuilder.builder();
        builder.host("localhost").port(8090).ssl(false).path("controller/rest/applications/Server%20&%20Infrastructure%20Monitoring/metric-data");
        builder.query("metric-path", "Application%20Infrastructure%20Performance%7CRoot%7CCustom%20Metrics%7CApache%7CApache1%7CHeartBeat");
        builder.query("time-range-type", "BEFORE_NOW");
        builder.query("duration-in-mins", "60");
        builder.query("output", "JSON");

        CloseableHttpResponse httpResponse = sendGET(builder.build());

        int statusCode = httpResponse.getStatusLine().getStatusCode();

        Assert.assertEquals("Invalid response code", 200, statusCode);


        BufferedReader reader = new BufferedReader(new InputStreamReader(
                httpResponse.getEntity().getContent()));

        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = reader.readLine()) != null) {
            response.append(inputLine);
        }
        reader.close();


        ObjectMapper mapper = new ObjectMapper();

        JsonNode jsonNode = mapper.readTree(response.toString());

        String metricName = jsonNode.get(0).get("metricName").getTextValue();
        int metricValue = jsonNode.get(0).get("metricValues").get(0).get("value").getIntValue();


        Assert.assertEquals("Invalid metric name", "Custom Metrics|Apache|Apache1|HeartBeat", metricName);

        Assert.assertEquals("Invalid metric value", 1, metricValue);

    }

    @Test
    public void testUptimeMetric() throws IOException {


        UrlBuilder builder = UrlBuilder.builder();
        builder.host("localhost").port(8090).ssl(false).path("controller/rest/applications/Server%20&%20Infrastructure%20Monitoring/metric-data");
        builder.query("metric-path", "Application%20Infrastructure%20Performance%7CRoot%7CCustom%20Metrics%7CApache%7CApache1%7CAvailability%7CServer%20Uptime%20%28sec%29");
        builder.query("time-range-type", "BEFORE_NOW");
        builder.query("duration-in-mins", "60");
        builder.query("output", "JSON");

        CloseableHttpResponse httpResponse = sendGET(builder.build());

        int statusCode = httpResponse.getStatusLine().getStatusCode();

        Assert.assertEquals("Invalid response code", 200, statusCode);


        BufferedReader reader = new BufferedReader(new InputStreamReader(
                httpResponse.getEntity().getContent()));

        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = reader.readLine()) != null) {
            response.append(inputLine);
        }
        reader.close();


        ObjectMapper mapper = new ObjectMapper();

        JsonNode jsonNode = mapper.readTree(response.toString());

        String metricName = jsonNode.get(0).get("metricName").getTextValue();
        int metricValue = jsonNode.get(0).get("metricValues").get(0).get("value").getIntValue();


        Assert.assertEquals("Invalid metric name", "Custom Metrics|Apache|Apache1|Availability|Server Uptime (sec)", metricName);

        Assert.assertTrue("Invalid metric value", 1 < metricValue);

    }

    private CloseableHttpResponse sendGET(String url) throws IOException {

        HttpGet httpGet = new HttpGet(url);

        httpGet.addHeader("User-Agent", USER_AGENT);
        CloseableHttpResponse httpResponse = httpClient.execute(httpGet);

        return httpResponse;
    }

}
