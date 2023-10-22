import axios from "axios";
import router from '../router/index'

const request = axios.create({
    baseURL: '/api/',
    timeout: 5000,
})
// 添加请求拦截器
request.interceptors.request.use(config => config, error => {
    console.error('req error', error)
})

// 添加响应拦截器
request.interceptors.response.use(response => response, error => {
    const errData = error.response.data
    if (errData.status === 401) {
        // 路由跳转到登录
        router.push('/login')
    }

    return Promise.reject(errData ? errData : {title:error.message});
})

export default request
