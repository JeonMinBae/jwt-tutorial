import axios, {AxiosRequestConfig, AxiosRequestHeaders, AxiosResponse} from "axios";

const axiosInstance = axios.create({
    baseURL: 'http://localhost:8080',
    timeout: 3000,
});

axiosInstance.interceptors.request.use((config: AxiosRequestConfig) => {
    const headers: AxiosRequestHeaders = {
        ...config.headers,
        Authorization: 'Bearer ' + (localStorage.getItem("token") || ''),
    };

    config.headers = headers;
    return config;
});

axiosInstance.interceptors.response.use(
    (response: AxiosResponse) => {
        return response;
    },
    error => {
        return Promise.reject(error);
    },
)

export default axiosInstance;