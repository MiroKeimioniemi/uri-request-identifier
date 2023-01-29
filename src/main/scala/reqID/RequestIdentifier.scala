package reqID

class RequestIdentifier:

  def identifyRequest(uri: String) =

    val scheme     = uri.takeWhile(_ != ':')
    val path       = uri.dropWhile(_ != '/').drop(2).takeWhile(_ != '?')
    val parameters = uri.dropWhile(_ != '?').tail.split('&').map(_.split('=')).map( x => x.head -> x.last).toMap

    def validScheme: Boolean =
      scheme == "visma-identity"

    def validPathAndParams: Boolean =
      val params = parameters.toArray

      path match
        case "login"   =>
          val validSource        = params.exists( ((key, value) => key == "source" && value.forall(_.isLetter)) )
          val correctNumOfParams = params.length == 1
          validSource && correctNumOfParams

        case "confirm" =>
          val validSource        = params.exists( ((key, value) => key == "source" && value.forall(_.isLetter)) )
          val validPaymentNumber = params.exists( ((key, value) => key == "paymentnumber" && value.forall(_.isDigit)) )
          val correctNumOfParams = params.length == 2
          validSource && validPaymentNumber && correctNumOfParams

        case "sign"    =>
          val validSource        = params.exists( ((key, value) => key == "source" && value.forall(_.isLetter)) )
          val validDocumentID    = params.exists( ((key, value) => key == "documentid" && value.forall(_.isValidChar)) )
          val correctNumOfParams = params.length == 2
          validSource && validDocumentID && correctNumOfParams

        case _         =>
          false

    if validScheme && validPathAndParams then
      (path, parameters)
    else
      "Invalid URI"

end RequestIdentifier


