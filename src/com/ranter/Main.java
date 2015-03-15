package com.ranter;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import com.couchbase.client.CouchbaseClient;
import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonObject;
import com.google.gson.Gson;

public class Main {

	public static void main(String[] args) {

		List<URI> nodes=null;
		try {
			nodes = Arrays.asList(new URI("http://127.0.0.1:8091/pools"));
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			CouchbaseClient client = new CouchbaseClient(nodes, "ranter", "");
			Gson gson = new Gson();
			Rant rant = new Rant(
					UUID.randomUUID(),
					"JerryS",
					"Why do they call it Ovaltine? The mug is round. The jar is round. They should call it Roundtine.",
					"rant");

			Boolean success = client.set(rant.getId().toString(),
					gson.toJson(rant)).get();
			
			client.shutdown();
		} catch (IOException | InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	

	}

}
