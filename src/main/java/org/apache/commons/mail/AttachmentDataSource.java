/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.commons.mail;

import javax.activation.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Proxy dataSource class which contain reference of MimePartDataSource for given attachment,
 * with revised type and name, in order delay the memory allocation for attachment until when the content of the attachment is needed.
 *
 * @since 1.3
 */
public class AttachmentDataSource implements DataSource {


    private String type;	// content-type
    private String name = "";
    private DataSource ds;


    public AttachmentDataSource(DataSource ds, String type, String name) {

        this.ds = ds;
        this.type = type;
        this.name = name;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        if (ds == null) {
            throw new IOException("no data available");
        } else{
            return ds.getInputStream();
        }
    }

    @Override
    public OutputStream getOutputStream() throws IOException {
        throw new IOException("cannot do this");
    }

    @Override
    public String getContentType() {
        return type;
    }

    @Override
    public String getName() {
        return name;
    }
}
