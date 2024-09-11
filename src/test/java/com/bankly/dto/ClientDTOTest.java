package com.bankly.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.io.File;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.UUID;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ClientDTOTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testShouldSerializeClientDTO() throws JsonProcessingException {

        ClientDTO client = new ClientDTO(
                UUID.fromString("550e8400-e29b-41d4-a716-446655440000"),
                "John",
                "Doe",
                LocalDate.of(1990, 10, 10),
                "john.doe@example.com",
                "+1234567890"
        );

        String json = objectMapper.writeValueAsString(client);
        assertThat(json).isNotNull();
        System.out.println("Serialized JSON: " + json);

        assertThat(json).contains("\"firstName\":\"John\"");
        assertThat(json).contains("\"lastName\":\"Doe\"");
        assertThat(json).contains("\"dob\":\"1990-10-10\"");
        assertThat(json).contains("\"email\":\"john.doe@example.com\"");
        assertThat(json).contains("\"phone\":\"+1234567890\"");
    }

    @Test
    public void testShouldDeserializeClientDTO() throws Exception {

        File file = Paths.get("src/test/resources/json/client/client_test_serialization.json").toFile();

        ClientDTO client = objectMapper.readValue(file, ClientDTO.class);

        assertNotNull(client);
        assertEquals(UUID.fromString("550e8400-e29b-41d4-a716-446655440000"), client.id());
        assertEquals("John", client.firstName());
        assertEquals("Doe", client.lastName());
        assertEquals(LocalDate.of(1990, 5, 20), client.dob());
        assertEquals("john.doe@example.com", client.email());
        assertEquals("+1234567890", client.phone());
    }
}
