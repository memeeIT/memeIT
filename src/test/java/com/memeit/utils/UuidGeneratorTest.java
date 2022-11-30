package com.memeit.utils;

import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class UuidGeneratorTest {

    @Test
    void should_generate_random_uuid_and_not_be_null() {

        // given
        String uuid = UuidGenerator.generateUuid();
        // when & then
        assertNotNull(uuid);
    }

    @Test
    void should_generate_uuid_in_pattern() {

        //given
        String regex =  "([a-zA-Z0-9]{8}-[a-zA-Z0-9]{4}-[a-zA-Z0-9]{4}-[a-zA-Z0-9]{4}-[a-zA-Z0-9]{12})";
        String uuid = UuidGenerator.generateUuid();

        // when & then
        assertEquals(true, Pattern.matches(regex, uuid));

    }
}