package com.gorest.api;

import com.gorest.utils.RandomGenerator;

public class ApiData 
{
	public static final String GOREST_PAYLOAD="./src/test/resources/json-payload/GoRest.json";
	public static final String MAIL="email";
	public static final String NAME="name";
	public static final String UNQUIE_MAIL_ID="JohnDoe" + System.currentTimeMillis() + "@example.com";
	public static final String UNQUIE_NAME = RandomGenerator.randomAlpha(8);
}
