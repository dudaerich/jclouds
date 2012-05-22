/**
 * Licensed to jclouds, Inc. (jclouds) under one or more
 * contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  jclouds licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.jclouds.date.internal;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import java.util.Date;

import org.jclouds.date.DateCodec;
import org.jclouds.date.internal.DateServiceDateCodecFactory.DateServiceRfc1123Codec;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * 
 * @author aled
 * 
 */
@Test(testName = "DateServiceDateCodecFactoryTest")
public class DateServiceDateCodecFactoryTest {

   private DateServiceDateCodecFactory simpleDateCodecFactory;
   private DateCodec rfc1123Codec;

   @BeforeMethod
   public void setUp() {
      simpleDateCodecFactory = new DateServiceDateCodecFactory(new DateServiceRfc1123Codec(
            new SimpleDateFormatDateService()));
      rfc1123Codec = simpleDateCodecFactory.rfc1123();
   }

   @Test
   public void testCodecForRfc1123() {
      Date date = new Date(1000);
      assertEquals(rfc1123Codec.toDate(rfc1123Codec.toString(date)), date);

      assertEquals(rfc1123Codec.toDate("Thu, 01 Dec 1994 16:00:00 GMT"), new Date(786297600000L));
   }

   @Test
   public void testCodecForRfc1123ThrowsParseExceptionWhenMalformed() {
      try {
         rfc1123Codec.toDate("wrong");
         fail();
      } catch (IllegalArgumentException e) {
      }
   }
}
