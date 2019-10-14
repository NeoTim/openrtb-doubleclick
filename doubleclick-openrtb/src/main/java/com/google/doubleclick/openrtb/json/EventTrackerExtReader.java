/*
 * Copyright 2018 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.doubleclick.openrtb.json;

import static com.google.openrtb.json.OpenRtbJsonUtils.endArray;
import static com.google.openrtb.json.OpenRtbJsonUtils.getCurrentName;
import static com.google.openrtb.json.OpenRtbJsonUtils.startArray;

import com.fasterxml.jackson.core.JsonParser;
import com.google.doubleclick.AdxExt;
import com.google.doubleclick.AdxExt.EventTrackerExt;
import com.google.doubleclick.AdxExt.EventTrackerExt.Context;
import com.google.openrtb.OpenRtb.NativeResponse.EventTracker;
import com.google.openrtb.json.OpenRtbJsonExtComplexReader;
import java.io.IOException;

/**
 * Reader for {@link EventTrackerExt}.
 */
class EventTrackerExtReader
    extends OpenRtbJsonExtComplexReader<EventTracker.Builder, EventTrackerExt.Builder> {

  public EventTrackerExtReader() {
    super(
        AdxExt.eventtrackers, /*isJsonObject=*/ false,
        "context", "verification_parameters", "vendor_key");
  }

  @Override protected void read(EventTrackerExt.Builder ext, JsonParser par) throws IOException {
    switch (getCurrentName(par)) {
      case "context":
        for (startArray(par); endArray(par); par.nextToken()) {
          Context value = Context.forNumber(par.getIntValue());
          if (checkEnum(value)) {
            ext.addContext(value);
          }
        }
        break;
      case "verification_parameters":
        ext.setVerificationParameters(par.nextTextValue());
        break;
      case "vendor_key":
        ext.setVendorKey(par.nextTextValue());
        break;
    }
  }
}
