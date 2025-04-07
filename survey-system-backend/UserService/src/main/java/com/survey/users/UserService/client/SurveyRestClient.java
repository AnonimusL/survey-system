package com.survey.users.UserService.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.survey.users.UserService.dto.for_target_user.SurveyDto;
import okhttp3.*;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class SurveyRestClient {

    public static final String URL = "http://localhost:8081/api";

    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");

    OkHttpClient client = new OkHttpClient();
    ObjectMapper objectMapper = new ObjectMapper();

    public List<SurveyDto> getSurveys(List<String> identifiers) throws IOException {

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        HttpUrl.Builder urlBuilder = HttpUrl.parse(URL + "/survey/target-user").newBuilder();

        String jsonBody = objectMapper.writeValueAsString(identifiers);
        RequestBody body = RequestBody.create(jsonBody, MediaType.parse("application/json"));

        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .post(body)
                .build();

        Call call = client.newCall(request);

        Response response = call.execute();

        if (response.code() == 200) {
            assert response.body() != null;
            String json = response.body().string();

            return objectMapper.readValue(json, new TypeReference<List<SurveyDto>>() {});
        }

        throw new RuntimeException();
    }
}
