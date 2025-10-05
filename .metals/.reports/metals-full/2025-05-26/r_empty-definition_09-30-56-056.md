error id: file://<WORKSPACE>/app/models/BlogRepo.scala:`<none>`.
file://<WORKSPACE>/app/models/BlogRepo.scala
empty definition using pc, found symbol in pc: `<none>`.
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 653
uri: file://<WORKSPACE>/app/models/BlogRepo.scala
text:
```scala
package models

import slick.jdbc.PostgresProfile.api._
import scala.concurrent.{Future, ExecutionContext}

// Case class for a blog post
case class BlogPost(id: Long, title: String, content: String)

// Table definition for BlogPosts
class BlogPostTable(tag: Tag) extends Table[BlogPost](tag, "blog_posts") {
  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def title = column[String]("title")
  def content = column[String]("content")

  def * = (id, title, content) <> ((BlogPost.apply _).tupled, BlogPost.unapply)
}

// BlogRepo object for database operations
object BlogRepo {
  // You must inject this or define it in your application
  va@@l db = Database.forConfig("mydb") // make sure you have mydb in your application.conf

  // Reference to the BlogPosts table
  val blogPosts = TableQuery[BlogPostTable]

  // Create a post
  def addPost(post: BlogPost)(implicit ec: ExecutionContext): Future[Long] = {
    db.run((blogPosts returning blogPosts.map(_.id)) += post)
  }

  // Get all posts
  def getAllPosts()(implicit ec: ExecutionContext): Future[Seq[BlogPost]] = {
    db.run(blogPosts.result)
  }

  // Get post by title
  def getPostByTitle(title: String)(implicit ec: ExecutionContext): Future[Option[BlogPost]] = {
    db.run(blogPosts.filter(_.title === title).result.headOption)
  }

  // Update a post by title
  def updatePost(oldTitle: String, updatedPost: BlogPost)(implicit ec: ExecutionContext): Future[Int] = {
    db.run(blogPosts.filter(_.title === oldTitle).update(updatedPost))
  }

  // Delete a post by title
  def deletePost(title: String)(implicit ec: ExecutionContext): Future[Int] = {
    db.run(blogPosts.filter(_.title === title).delete)
  }
}

```


#### Short summary: 

empty definition using pc, found symbol in pc: `<none>`.