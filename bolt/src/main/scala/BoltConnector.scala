/**
  * Created by Rodrigo Lima on 10-May-16.
  */

import org.neo4j.driver.v1._

trait BoltConnector {
  private val driver = GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j", "123qwe"))

  var session: Session = _

  protected def openSession(): Unit = session = driver.session()

  def closeSession(): Unit = session.close()
}

class BoltConnectorImpl extends BoltConnector {

  openSession()

  def executeStatement(statement: String) = {

    val trx = session.beginTransaction()
    println("Session: " + session.toString)

    try {
      trx.run(statement)
      println("Trx: " + trx.toString)
    }
    catch {
      case e: Throwable => {
        println(e.getMessage)
        trx.failure()
      }
    }
    finally {
      trx.success()
      trx.close()
    }

  }
}
