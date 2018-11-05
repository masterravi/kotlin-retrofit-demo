package co.imdbreviews.views.login

import org.junit.After
import org.junit.Assert
import org.junit.Before

import org.junit.Test

class LoginPresenterTest {
    lateinit var loginPresenter:LoginPresenter;
    @Before
    fun setUp() {
        loginPresenter = LoginPresenter()
    }

    @Test
    fun maxLoginAttempt(){

        Assert.assertEquals(1,loginPresenter.incrementLoginAttempt())
        Assert.assertEquals(2,loginPresenter.incrementLoginAttempt())
        Assert.assertEquals(3,loginPresenter.incrementLoginAttempt())
        Assert.assertTrue(loginPresenter.isLoginAttemptExceeded())
    }
}