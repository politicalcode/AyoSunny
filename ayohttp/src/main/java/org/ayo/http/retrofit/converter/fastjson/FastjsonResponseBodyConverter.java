/*
 * Copyright (C) 2015 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ayo.http.retrofit.converter.fastjson;

import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

final class FastjsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {

  private Type type;

  FastjsonResponseBodyConverter(Type type) {
    this.type = type;
  }

  @Override public T convert(ResponseBody value) throws IOException {
    return JSON.parseObject(value.bytes(), type);
  }
}
