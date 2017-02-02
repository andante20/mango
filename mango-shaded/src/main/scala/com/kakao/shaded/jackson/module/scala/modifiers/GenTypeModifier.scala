package com.kakao.shaded.jackson.module.scala.modifiers

import java.lang.reflect.{ParameterizedType, Type}

import com.kakao.shaded.jackson.databind.`type`.SimpleType

import scala.language.existentials

private [modifiers] trait GenTypeModifier {

  // Workaround for http://jira.codehaus.org/browse/JACKSON-638
  protected def UNKNOWN = SimpleType.construct(classOf[AnyRef])

  protected def classObjectFor(jdkType: Type) = jdkType match {
    case cls: Class[_] => Some(cls)
    case pt: ParameterizedType => pt.getRawType match {
      case cls: Class[_] => Some(cls)
      case _ => None
    }
    case _ => None
  }

}