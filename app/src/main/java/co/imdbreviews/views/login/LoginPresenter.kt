package co.imdbreviews.views.login

class LoginPresenter{
    val  MAX_LOGIN_ATTEMPT:Int = 3
    var loginAttempt:Int = 0

    fun incrementLoginAttempt(): Int {
        return ++loginAttempt
    }

    fun isLoginAttemptExceeded(): Boolean {
        return loginAttempt>= MAX_LOGIN_ATTEMPT
    }

}