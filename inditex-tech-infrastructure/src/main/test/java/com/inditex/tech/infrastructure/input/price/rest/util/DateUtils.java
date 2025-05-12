package com.inditex.tech.infrastructure.input.price.rest.util;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class DateUtils {

  private static final String DATE_FORMAT_PATTERN = "yyyy-MM-dd-HH.mm.ss";

  /**
   * Parses a string date in the format "yyyy-MM-dd-HH.mm.ss" to an OffsetDateTime with UTC zone offset.
   *
   * @param input the date string to parse
   * @return the parsed OffsetDateTime
   */
  public static OffsetDateTime parseCustomDateTime(final String input) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN);
    LocalDateTime localDateTime = LocalDateTime.parse(input, formatter);
    return localDateTime.atOffset(ZoneOffset.UTC);
  }
  
}