export default {
    user: {
        isAuthenticated() {
            return this.getToken() !== null
        },
        getToken() {
            const str = sessionStorage.getItem("token")
            if (str === undefined || str == null) {
                return null
            } else {
                return str
            }
        },
        setToken(token) {
            sessionStorage.setItem("token", token)
        },
        setUser(user) {
            if (user) {
                sessionStorage.removeItem("user")
                return
            }
            sessionStorage.setItem("user", JSON.stringify(user))
        },
        getUser() {
            const str = sessionStorage.getItem("user")
            if (str) {
                return JSON.parse(str)
            } else {
                return null
            }
        }

    }

}