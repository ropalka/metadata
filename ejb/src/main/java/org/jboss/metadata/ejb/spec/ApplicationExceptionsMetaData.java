/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2013, Red Hat, Inc., and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.jboss.metadata.ejb.spec;

import org.jboss.metadata.javaee.support.AbstractMappedMetaData;
import org.jboss.metadata.merge.MergeUtil;
import org.jboss.metadata.merge.javaee.support.IdMetaDataImplMerger;

/**
 * ApplicationExceptionsMetaData.
 *
 * @author <a href="adrian@jboss.com">Adrian Brock</a>
 * @version $Revision: 1.1 $
 */
public class ApplicationExceptionsMetaData extends AbstractMappedMetaData<ApplicationExceptionMetaData> {
    /**
     * The serialVersionUID
     */
    private static final long serialVersionUID = -5880146271705804091L;

    /**
     * Create a new ApplicationExceptionsMetaData.
     */
    public ApplicationExceptionsMetaData() {
        super("application exception class");
    }

    public void merge(ApplicationExceptionsMetaData override, ApplicationExceptionsMetaData original) {
        IdMetaDataImplMerger.merge(this, override, original);
        MergeUtil.merge(this, override, original);
    }
}
