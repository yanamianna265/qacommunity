# 简易问答社区 - API接口文档

## 统一响应格式

```json
{
    "code": 200,
    "message": "成功",
    "data": { ... }
}
```

### 响应码说明
| code | 说明 |
|------|------|
| 200 | 成功 |
| 400 | 参数错误 |
| 401 | 未授权/登录失败 |
| 404 | 资源不存在 |
| 409 | 资源冲突（如用户名已存在） |
| 500 | 服务器内部错误 |

---

## 一、用户模块 `/api/user`

### 1.1 用户注册
```
POST /api/user/register
```

**请求参数：**
| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| username | String | 是 | 用户名(3-20字符) |
| password | String | 是 | 密码(6-20字符) |
| email | String | 否 | 邮箱 |

**响应示例：**
```json
{
    "code": 200,
    "message": "成功",
    "data": {
        "userId": 1,
        "username": "alice",
        "email": "alice@example.com"
    }
}
```

### 1.2 用户登录
```
POST /api/user/login
```

**请求参数：**
| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| username | String | 是 | 用户名 |
| password | String | 是 | 密码 |

**响应示例：**
```json
{
    "code": 200,
    "message": "成功",
    "data": "eyJhbGciOiJIUzI1NiJ9..."
}
```

### 1.3 获取用户信息
```
GET /api/user/info
Authorization: Bearer <token>
```

---

## 二、问题模块 `/api/question`

### 2.1 发布问题
```
POST /api/question
Authorization: Bearer <token>
```

**请求参数：**
| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| title | String | 是 | 问题标题 |
| content | String | 是 | 问题内容 |
| tag | String | 否 | 标签 |

### 2.2 问题列表（分页）
```
GET /api/question/list?page=1&size=10
```

**响应示例：**
```json
{
    "code": 200,
    "message": "成功",
    "data": {
        "total": 100,
        "page": 1,
        "size": 10,
        "list": [
            {
                "questionId": 1,
                "title": "Java如何实现单例？",
                "content": "...",
                "tag": "Java",
                "username": "alice",
                "createdAt": "2024-01-01 12:00:00"
            }
        ]
    }
}
```

### 2.3 问题详情
```
GET /api/question/{id}
```

### 2.4 按标签查询
```
GET /api/question/tag/{tag}
```

---

## 三、回答模块 `/api/answer`

### 3.1 发布回答
```
POST /api/answer
Authorization: Bearer <token>
```

**请求参数：**
| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| questionId | Integer | 是 | 问题ID |
| content | String | 是 | 回答内容 |

### 3.2 问题回答列表
```
GET /api/answer/list/{questionId}?page=1&size=10
```

---

## 四、评论模块 `/api/comment`

### 4.1 发布评论
```
POST /api/comment
Authorization: Bearer <token>
```

**请求参数：**
| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| answerId | Integer | 是 | 回答ID |
| content | String | 是 | 评论内容 |

### 4.2 回答评论列表
```
GET /api/comment/list/{answerId}?page=1&size=10
```

---

## 五、热榜模块 `/api/rank`

### 5.1 热门问题列表
```
GET /api/rank/hot?limit=20
```

---

## 六、内容审核 `/api/moderation`

### 6.1 敏感词检测（内部接口）
```
POST /api/moderation/check
```

**请求参数：**
| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| content | String | 是 | 待检测内容 |

**响应示例：**
```json
{
    "code": 200,
    "message": "成功",
    "data": {
        "passed": false,
        "reason": "包含敏感词: xxx"
    }
}
```

---

## 简历亮点（API设计层面）

> 设计统一的 RESTful API 规范，采用标准响应格式（code/message/data），实现完善的参数校验与全局异常处理，提升接口可用性与用户体验。
