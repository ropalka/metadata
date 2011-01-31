/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2009, Red Hat Middleware LLC, and individual contributors
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
package org.jboss.metadata.annotation.creator.ejb;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.Collection;

import javax.ejb.Schedule;

import org.jboss.metadata.annotation.creator.AbstractFinderUser;
import org.jboss.metadata.annotation.creator.EjbProcessorUtils;
import org.jboss.metadata.annotation.creator.Processor;
import org.jboss.metadata.annotation.creator.ProcessorUtils;
import org.jboss.metadata.annotation.finder.AnnotationFinder;
import org.jboss.metadata.common.ejb.IScheduleTarget;
import org.jboss.metadata.ejb.spec.NamedMethodMetaData;
import org.jboss.metadata.ejb.spec.ScheduleMetaData;
import org.jboss.metadata.ejb.spec.TimerMetaData;

/**
 * Processes {@link Schedule} annotation on bean methods.
 *
 * @author Jaikiran Pai
 * @version $Revision: $
 */
public class ScheduleProcessor extends AbstractFinderUser implements Processor<IScheduleTarget, Method>
{

   /**
    * 
    * @param finder
    */
   public ScheduleProcessor(AnnotationFinder<AnnotatedElement> finder)
   {
      super(finder);
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public Collection<Class<? extends Annotation>> getAnnotationTypes()
   {
      return ProcessorUtils.createAnnotationSet(Schedule.class);
   }

   /**
    * Processes the passed <code>method</code> for {@link Schedule} annotation
    * and creates {@link TimerMetaData} out of it for the bean
    */
   @Override
   public void process(IScheduleTarget scheduleTargetBeanMetaData, Method method)
   {
      Schedule schedule = finder.getAnnotation(method, Schedule.class);
      if(schedule == null)
      {
         return;
      }
      // create timer metadata
      TimerMetaData timerMetadata = new TimerMetaData();
      timerMetadata.setInfo(schedule.info());
      timerMetadata.setPersistent(schedule.persistent());
      timerMetadata.setTimezone(schedule.timezone());

      // create a timeout method metadata for the method on which this
      // @Schedule is present
      NamedMethodMetaData timeoutMethod = new NamedMethodMetaData();
      timeoutMethod.setMethodName(method.getName());
      timeoutMethod.setMethodParams(EjbProcessorUtils.getMethodParameters(method));
      timerMetadata.setTimeoutMethod(timeoutMethod);
      
      // create schedule metadata
      ScheduleMetaData scheduleMetadata = new ScheduleMetaData(schedule);
      timerMetadata.setSchedule(scheduleMetadata);

      // finally set the timer metadata in the bean
      scheduleTargetBeanMetaData.addTimer(timerMetadata);
   }

}
