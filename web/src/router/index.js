import {createRouter, createWebHistory} from 'vue-router'
import session from '../utils/sessionStorage.js'
const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: '/',
            redirect: '/index'
        },
        {
            path: '/index',
            name: 'index',
            // route level code-splitting
            // this generates a separate chunk (About.[hash].js) for this route
            // which is lazy-loaded when the route is visited.
            component: () => import('../views/model/ProjectList.vue')
        },
        {
            path: '/login',
            name: 'login',
            meta: {public: true},
            component: () => import('../views/sys/Login.vue')
        },
        {
            path: '/project/:id',
            name: 'project-detail',
            component: () => import('../views/model/ModelIndex.vue')
        },
        {
            path: '/test',
            component: () => import('../views/model/Test.vue')
        }
    ]
})

router.beforeEach((to, from, next) => {
    // 使用了cookie 不再设置导航守卫
    next()
    // const authed = session.user.isAuthenticated();
    // if (!to.meta.public && !authed) {
    //     // 校验令牌
    //     console.info('校验令牌', authed)
    //     next({name: 'login'})
    // } else {
    //     next()
    // }
})

export default router
