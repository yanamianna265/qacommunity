# 简易问答社区 - 后端项目

## 项目简介
一个类似知乎/Stack Overflow的简易问答社区后端服务，支持用户管理、问题发布、回答、评论等核心功能。

## 技术栈
- Spring Boot 4.0.3
- MyBatis
- MySQL 8.0+
- JWT 认证
- Lombok
- Maven
- Spring Scheduling（定时任务）

## 环境准备
- JDK 17+
- MySQL 8.0+
- Maven 3.6+

## 初始化数据库
```bash
mysql -u root -p qa_community < doc/db_design/schema.sql
```

## 配置
编辑 `src/main/resources/application.yml`，修改数据库连接信息：
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/qa_community?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true&characterEncoding=utf8
    username: root
    password: your_mysql_password
```

## 运行项目
```bash
mvn spring-boot:run
```

## 接口文档
测试文件：`doc/postman/`

## 已完成功能

### Day 1 - 数据库设计
- ✅ 完成三层嵌套结构的数据库建模（Question-Answer-Comment）
- ✅ 设计合理的索引策略优化查询性能
- ✅ 通过级联删除确保数据一致性
- ✅ 使用 utf8mb4 字符集支持国际化内容

### Day 2 - 用户模块
- ✅ 完成 Spring Boot 项目初始化
- ✅ 实现用户实体与 Mapper
- ✅ 实现统一响应格式与全局异常处理
- ✅ 实现用户注册接口（含参数校验、密码加密）
- ✅ 实现用户登录接口（JWT 认证）

### Day 3 - 问题与回答模块
- ✅ 实现问题发布接口（需登录）
- ✅ 实现问题列表接口（分页查询）
- ✅ 实现问题详情接口
- ✅ 实现按标签查询接口
- ✅ 实现回答发布接口（需登录）
- ✅ 实现回答列表接口（分页查询）
- ✅ 支持关联数据加载（用户名）

### Day 4 - 评论模块与内容审核
- ✅ 实现评论发布接口（需登录）
- ✅ 实现评论列表接口（分页查询）
- ✅ 实现内容审核服务（敏感词过滤）
- ✅ 支持中英文敏感词检测

### Day 5 - 热榜排序与定时任务
- ✅ 实现热榜服务（HotRankService）
- ✅ 设计并实现热度计算算法
- ✅ 实现定时任务每小时自动更新热榜
- ✅ 提供手动刷新热榜接口

## 接口列表

| 接口 | 方法 | 说明 | 认证 |
|------|------|------|------|
| `/api/user/register` | POST | 用户注册 | 否 |
| `/api/user/login` | POST | 用户登录 | 否 |
| `/api/question` | POST | 发布问题 | 是 |
| `/api/question/list` | GET | 问题列表（分页） | 否 |
| `/api/question/{id}` | GET | 问题详情 | 否 |
| `/api/question/tag/{tag}` | GET | 按标签查询 | 否 |
| `/api/answer` | POST | 发布回答 | 是 |
| `/api/answer/list/{questionId}` | GET | 回答列表（分页） | 否 |
| `/api/comment` | POST | 发布评论 | 是 |
| `/api/comment/list/{answerId}` | GET | 评论列表（分页） | 否 |
| `/api/moderation/check` | POST | 敏感词检测 | 否 |
| `/api/rank/hot` | GET | 获取热榜列表 | 否 |
| `/api/rank/refresh` | POST | 手动刷新热榜 | 否 |

## 项目结构
```
src/main/java/com/example/qacommunity/
├── common/          # 通用类（Result, ResultCode, PageResult）
├── config/          # 配置类（JwtConfig, SchedulerConfig）
├── controller/      # 控制器（User, Question, Answer, Comment, Moderation, HotRank）
├── entity/          # 实体类（User, Question, Answer, Comment, QuestionHot）
├── exception/       # 异常处理
├── mapper/          # MyBatis Mapper接口
├── service/         # 业务逻辑接口及实现
├── util/            # 工具类（JwtUtil）
└── vo/              # 视图对象（QuestionVO, AnswerVO, CommentVO, HotQuestionVO）
```

## 待完成功能
- [ ] Day 6 - Redis缓存优化
- [ ] Day 7 - Elasticsearch搜索（可选）

## 注意事项
- Apifox/Postman 测试时注意区分**路径参数**和 **Query 参数**
- 例如 `/api/comment/list/{answerId}` 中 `{answerId}` 是路径参数
- 认证接口需要在请求头中添加 `Authorization: Bearer <token>`
- 热榜功能首次使用需先调用 `/api/rank/refresh` 初始化数据
