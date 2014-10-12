/*   Copyright (C) 2013-2014 Computer Sciences Corporation
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
 * limitations under the License. */

package ezbake.ins.web;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import org.apache.thrift.TBase;
import org.apache.thrift.TException;
import org.apache.thrift.TSerializer;
import org.apache.thrift.protocol.TSimpleJSONProtocol;

@Produces("application/json")
@Provider
public class ThriftMessageBodyWriter implements MessageBodyWriter<TBase> {
        @Override
        public boolean isWriteable(Class<?> type, Type genericType,
                                   Annotation[] annotations, MediaType mediaType) {
            return TBase.class.isAssignableFrom(type);
        }

        @Override
        public long getSize(TBase thriftObject, Class<?> type, Type genericType,
                            Annotation[] annotations, MediaType mediaType) {
            try {
                TSerializer serializer = new TSerializer(new TSimpleJSONProtocol.Factory());
                return serializer.toString(thriftObject).length();
            } catch(TException ex) {
                return 0;
            }
        }

        @Override
        public void writeTo(TBase thriftObject,
                            Class<?> type,
                            Type genericType,
                            Annotation[] annotations,
                            MediaType mediaType,
                            MultivaluedMap<String, Object> httpHeaders,
                            OutputStream entityStream)
                throws IOException, WebApplicationException {

            try {
                TSerializer serializer = new TSerializer(new TSimpleJSONProtocol.Factory());
                entityStream.write(serializer.serialize(thriftObject));
                entityStream.flush();
            } catch (TException thriftException) {
                throw new IOException("Failed to serialize object to JSON", thriftException);
            }
        }
    }
