error id: file://<WORKSPACE>/app/controllers/HomeController.scala:`<none>`.
file://<WORKSPACE>/app/controllers/HomeController.scala
empty definition using pc, found symbol in pc: `<none>`.
empty definition using semanticdb
empty definition using fallback
non-local guesses:
	 -javax/inject.
	 -javax/inject#
	 -javax/inject().
	 -play/api.
	 -play/api#
	 -play/api().
	 -play/api/mvc.
	 -play/api/mvc#
	 -play/api/mvc().
	 -models.
	 -models#
	 -models().
	 -.
	 -#
	 -().
	 -scala/Predef.
	 -scala/Predef#
	 -scala/Predef().
offset: 796
uri: file://<WORKSPACE>/app/controllers/HomeController.scala
text:
```scala
package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import models._

@Singleton
class HomeController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  def index() = Action {
    Ok(views.html.index(BlogRepo.all()))
  }

  def newPostForm = Action {
    Ok(views.html.newPost())
  }

  def addPost() = Action(parse.formUrlEncoded) { request =>
    val data = request.body
    val title = data("title").head
    val content = data("content").head
    BlogRepo.add(BlogPost(title, content))
    Redirect(routes.HomeController.index())
  }

  def deletePost(title: String) = Action {
    BlogRepo.delete(title)
    Redirect(routes.HomeController.index())
  }

  def editPostForm(title: String) = Action {
  BlogRepo.all().find(_.titl@@e == title) match {
    case Some(post) => Ok(views.html.editPost(post))
    case None => Redirect(routes.HomeController.index())
  }
}

def updatePost(oldTitle: String) = Action(parse.formUrlEncoded) { request =>
  val data = request.body
  val newTitle = data("title").head
  val newContent = data("content").head
  BlogRepo.update(oldTitle, BlogPost(newTitle, newContent))
  Redirect(routes.HomeController.index())
}

}
```


#### Short summary: 

empty definition using pc, found symbol in pc: `<none>`.