// Converter.java

// To use this code, add the following Maven dependency to your project:
//
//
//     com.fasterxml.jackson.core     : jackson-databind          : 2.9.0
//     com.fasterxml.jackson.datatype : jackson-datatype-jsr310   : 2.9.0
//
// Import this package:
//
//     import com.apiverve.data.Converter;
//
// Then you can deserialize a JSON string with
//
//     SWIFTCodeLookupData data = Converter.fromJsonString(jsonString);

package com.apiverve.swiftlookup.data;

import java.io.IOException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.*;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

public class Converter {
    // Date-time helpers

    private static final DateTimeFormatter DATE_TIME_FORMATTER = new DateTimeFormatterBuilder()
            .appendOptional(DateTimeFormatter.ISO_DATE_TIME)
            .appendOptional(DateTimeFormatter.ISO_OFFSET_DATE_TIME)
            .appendOptional(DateTimeFormatter.ISO_INSTANT)
            .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SX"))
            .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssX"))
            .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
            .toFormatter()
            .withZone(ZoneOffset.UTC);

    public static OffsetDateTime parseDateTimeString(String str) {
        return ZonedDateTime.from(Converter.DATE_TIME_FORMATTER.parse(str)).toOffsetDateTime();
    }

    private static final DateTimeFormatter TIME_FORMATTER = new DateTimeFormatterBuilder()
            .appendOptional(DateTimeFormatter.ISO_TIME)
            .appendOptional(DateTimeFormatter.ISO_OFFSET_TIME)
            .parseDefaulting(ChronoField.YEAR, 2020)
            .parseDefaulting(ChronoField.MONTH_OF_YEAR, 1)
            .parseDefaulting(ChronoField.DAY_OF_MONTH, 1)
            .toFormatter()
            .withZone(ZoneOffset.UTC);

    public static OffsetTime parseTimeString(String str) {
        return ZonedDateTime.from(Converter.TIME_FORMATTER.parse(str)).toOffsetDateTime().toOffsetTime();
    }
    // Serialize/deserialize helpers

    public static SWIFTCodeLookupData fromJsonString(String json) throws IOException {
        return getObjectReader().readValue(json);
    }

    public static String toJsonString(SWIFTCodeLookupData obj) throws JsonProcessingException {
        return getObjectWriter().writeValueAsString(obj);
    }

    private static ObjectReader reader;
    private static ObjectWriter writer;

    private static void instantiateMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        SimpleModule module = new SimpleModule();
        module.addDeserializer(OffsetDateTime.class, new JsonDeserializer<OffsetDateTime>() {
            @Override
            public OffsetDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
                String value = jsonParser.getText();
                return Converter.parseDateTimeString(value);
            }
        });
        mapper.registerModule(module);
        reader = mapper.readerFor(SWIFTCodeLookupData.class);
        writer = mapper.writerFor(SWIFTCodeLookupData.class);
    }

    private static ObjectReader getObjectReader() {
        if (reader == null) instantiateMapper();
        return reader;
    }

    private static ObjectWriter getObjectWriter() {
        if (writer == null) instantiateMapper();
        return writer;
    }
}

// SWIFTCodeLookupData.java

package com.apiverve.swiftlookup.data;

import com.fasterxml.jackson.annotation.*;

public class SWIFTCodeLookupData {
    private long count;
    private Bank[] banks;

    @JsonProperty("count")
    public long getCount() { return count; }
    @JsonProperty("count")
    public void setCount(long value) { this.count = value; }

    @JsonProperty("banks")
    public Bank[] getBanks() { return banks; }
    @JsonProperty("banks")
    public void setBanks(Bank[] value) { this.banks = value; }
}

// Bank.java

package com.apiverve.swiftlookup.data;

import com.fasterxml.jackson.annotation.*;

public class Bank {
    private String bank;
    private String city;
    private String branch;
    private String swiftCode;
    private String country;
    private String countryCode;

    @JsonProperty("bank")
    public String getBank() { return bank; }
    @JsonProperty("bank")
    public void setBank(String value) { this.bank = value; }

    @JsonProperty("city")
    public String getCity() { return city; }
    @JsonProperty("city")
    public void setCity(String value) { this.city = value; }

    @JsonProperty("branch")
    public String getBranch() { return branch; }
    @JsonProperty("branch")
    public void setBranch(String value) { this.branch = value; }

    @JsonProperty("swift_code")
    public String getSwiftCode() { return swiftCode; }
    @JsonProperty("swift_code")
    public void setSwiftCode(String value) { this.swiftCode = value; }

    @JsonProperty("country")
    public String getCountry() { return country; }
    @JsonProperty("country")
    public void setCountry(String value) { this.country = value; }

    @JsonProperty("country_code")
    public String getCountryCode() { return countryCode; }
    @JsonProperty("country_code")
    public void setCountryCode(String value) { this.countryCode = value; }
}