/*
 * Copyright 2019 HM Revenue & Customs
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

package uk.gov.hmrc.decisionservice.util

import java.io.IOException

import scala.io.Source
import scala.util.{Failure, Try}

object FileTokenizer {
  private val SEPARATOR = ','
  val COMMENT_CHAR: Char = '#'

  private def using[R <: { def close(): Unit }, B](resource: R)(f: R => B): B = try { f(resource) } finally { resource.close() }

  def tokenize(path:String): Try[List[List[String]]] = {
    val is = getClass.getResourceAsStream(path)
    if (is == null) {
      Failure(new IOException(s"resource not found: ${path}"))
    }
    else {
      Try(using(Source.fromInputStream(is)) { source =>
        source.getLines.filter(l => {
          !l.trim.isEmpty && l.charAt(0) != COMMENT_CHAR
        }).map(_.split(SEPARATOR).map(_.trim).toList).toList
      })
    }
  }

  def tokenizeWithTrailingSeparator(path:String): Try[List[List[String]]] = {
    def handleTrailingSeparator(l:String):String = if (l.last == SEPARATOR) l + " " else l
    val is = getClass.getResourceAsStream(path)
    if (is == null) {
      Failure(new IOException(s"resource not found: ${path}"))
    }
    else {
      Try(using(Source.fromInputStream(is)) { source =>
        source.getLines.filter(l => {
          !l.trim.isEmpty && l.charAt(0) != COMMENT_CHAR
        }).map(handleTrailingSeparator(_).split(SEPARATOR).map(_.trim).toList).toList
      })
    }
  }

}
