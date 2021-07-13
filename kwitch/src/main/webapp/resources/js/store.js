const store = {
    state: {
        isLogin:false,
    },
    setLoginUser () {
        this.isLogin = true;
    },
    clearLoginUser () {
        this.isLogin = false;
    }
}