package com.escola.alunos.service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.escola.alunos.model.Contato;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.GeocodingApiRequest;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.Geometry;
import com.google.maps.model.LatLng;

@Service
public class GeolocalizacaoService {
	
	public List<Double> obterLagitudeLong(Contato contato) throws ApiException, InterruptedException, IOException{
		GeoApiContext context = new GeoApiContext.Builder()
				.apiKey("AIzaSyCAdjEwDOQDya_cICxXGC7aL9WRv1NN2K0").build();
		
		GeocodingApiRequest request = GeocodingApi.newRequest(context).address(contato.getEndereco());
		
//		String enderecoRequest = request.toString();
//		
//		GeocodingResult[] results =  GeocodingApi.geocode(context,enderecoRequest).await();
		
		GeocodingResult[] results = request.await();
		
		GeocodingResult resultado = results[0];
		
		 Geometry geometry = resultado.geometry;
		 LatLng location = geometry.location;
		
//		context.shutdown();
		
		return Arrays.asList(location.lat, location.lng);
		
	}
}
