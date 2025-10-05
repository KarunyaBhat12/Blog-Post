package models

import slick.jdbc.SQLiteProfile.api._
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

case class BlogPost(id: Long, title: String, content: String)

class BlogTable(tag: Tag) extends Table[BlogPost](tag, "BLOGPOST") {
  def id = column[Long]("ID", O.PrimaryKey, O.AutoInc)
  def title = column[String]("TITLE")
  def content = column[String]("CONTENT")

  def * = (id, title, content) <> (BlogPost.tupled, BlogPost.unapply)
}

object BlogRepo {
  val db = Database.forConfig("slick.dbs.default.db")
  val blogPosts = TableQuery[BlogTable]

  def init(): Future[Unit] = db.run(blogPosts.schema.createIfNotExists)

  def all(): Future[Seq[BlogPost]] = db.run(blogPosts.result)

  def add(post: BlogPost): Future[Int] = db.run(blogPosts += post)

  def findByTitle(title: String): Future[Option[BlogPost]] = {
    db.run(blogPosts.filter(_.title === title).result.headOption)
  }

  def updatePost(oldTitle: String, updatedPost: BlogPost): Future[Int] = {
    val query = blogPosts.filter(_.title === oldTitle)
                         .map(p => (p.title, p.content))
                         .update((updatedPost.title, updatedPost.content))
    db.run(query)
  }

  def deleteByTitle(title: String): Future[Int] = {
    db.run(blogPosts.filter(_.title === title).delete)
  }
}
