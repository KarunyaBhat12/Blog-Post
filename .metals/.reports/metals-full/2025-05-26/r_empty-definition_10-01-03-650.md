error id: file://<WORKSPACE>/app/models/UserRepo.scala:`<none>`.
file://<WORKSPACE>/app/models/UserRepo.scala
empty definition using pc, found symbol in pc: `<none>`.
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 93
uri: file://<WORKSPACE>/app/models/UserRepo.scala
text:
```scala
// app/models/UserTable.scala or app/models/UserRepo.scala
package models
import models.User
@@
import slick.jdbc.SQLiteProfile.api._
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

//case class User(id: Long, username: String, password: String, role: String)

class UserTable(tag: Tag) extends Table[User](tag, "USERS") {
  def id = column[Long]("ID", O.PrimaryKey, O.AutoInc)
  def username = column[String]("USERNAME", O.Unique)
  def password = column[String]("PASSWORD")
  def role = column[String]("ROLE")

  def * = (id, username, password, role) <> (User.tupled, User.unapply)
}

object UserRepo {
  val db = Database.forConfig("slick.dbs.default.db")
  val users = TableQuery[UserTable]

  def init(): Future[Unit] = db.run(users.schema.createIfNotExists)

  def findByUsername(username: String): Future[Option[User]] =
    db.run(users.filter(_.username === username).result.headOption)

  def add(user: User): Future[Int] = db.run(users += user)

  // ✅ Add this method
  def createAdminUser(): Future[Int] = {
    val admin = User(0, "admin", "adminpass", "admin")
    db.run(users += admin)
  }
}

```


#### Short summary: 

empty definition using pc, found symbol in pc: `<none>`.