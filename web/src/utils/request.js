import axios from "axios";
import router from '../router/index'
import {message} from "ant-design-vue";

const request = axios.create({
    baseURL: '/api/',
    timeout: 5000,
})
// 添加请求拦截器
request.interceptors.request.use(function (config) {
    return config;
}, function (error) {
    console.error('请求拦截器记录错误', error)
})

// 添加响应拦截器
request.interceptors.response.use(function (response) {
    return response;
}, function (error) {
    const errData = error.response.data
    if (errData.status === 500) {
        message.error(errData.title)
        return
    } else if (errData.status === 401) {
        // 路由跳转到登录
        router.push('/login')
    }

    console.error('错误请求回执', error)
    return Promise.reject(error);
})

export default request
