/**
  * Created by Rodrigo Lima on 09/05/2016.
  */

object Main {
  def main(args: Array[String]) {

    //First connection
    new Thread(new Runnable {
      def run(): Unit = {
        val bolt = new BoltConnectorImpl()
        (1 to 100).foreach(i => {
          bolt.executeStatement(s"MERGE(:Person{name:'Rodrigo-%s'})".format(i))
        })
        bolt.closeSession()
      }
    }).start()


    //Second connection
    new Thread(new Runnable {
      def run(): Unit = {
        val bolt = new BoltConnectorImpl()
        (1 to 100).foreach(i => {
          bolt.executeStatement(s"MERGE(:Person{name:'Natacha-%s'})".format(i))
        })
        bolt.closeSession()
      }
    }).start()


    //Third connection
    new Thread(new Runnable {
      def run(): Unit = {
        val bolt = new BoltConnectorImpl()
        (1 to 100).foreach(i => {
          val statement =
            (s"MERGE (p1:Person{name:'Rodrigo-%s'}) " +
              s"MERGE (p2:Person{name:'Natacha-%s'}) " +
              s"MERGE (p1)-[:KNOW]->(p2)")
              .format(i, i)
          bolt.executeStatement(statement)
        })
        bolt.closeSession()
      }
    }).start()

  }
}
