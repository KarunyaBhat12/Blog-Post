package controllers

import javax.inject._
import play.api.mvc._
import models._
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class HomeController @Inject()(val controllerComponents: ControllerComponents)(implicit ec: ExecutionContext) extends BaseController {

  BlogRepo.init()  // Ensures table creation on startup

  def index() = Action.async {
    BlogRepo.all().map(posts => Ok(views.html.index(posts.toList)))
  }

  def newPostForm = Action {
    Ok(views.html.newPost())
  }

  def addPost = Action.async { implicit request =>
  val data = request.body.asFormUrlEncoded.get
  val title = data("title").head
  val content = data("content").head
  BlogRepo.add(BlogPost(0L, title, content)).map { _ =>
    Redirect(routes.HomeController.index())
  }
}

def deletePost(title: String) = Action.async {
  BlogRepo.deleteByTitle(title).map { _ =>
    Redirect(routes.HomeController.index())
  }
}


  def editPostForm(title: String) = Action.async {
    BlogRepo.findByTitle(title).map {
      case Some(post) => Ok(views.html.editPost(post))
      case None       => NotFound("Post not found")
    }
  }

  def updatePost(oldTitle: String) = Action.async { implicit request =>
  val formData = request.body.asFormUrlEncoded
  val newTitle = formData.get("title").head
  val content = formData.get("content").head

  val updated = BlogPost(0, newTitle, content) // Replace 0 with actual ID if needed
  BlogRepo.updatePost(oldTitle, updated).map { _ =>
    Redirect(routes.HomeController.index())
  }
}


}