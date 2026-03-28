# 简易问答社区 - 数据库ER图

## 一、实体关系总览

```
┌─────────────┐       ┌─────────────┐       ┌─────────────┐       ┌─────────────┐
│    User     │       │  Question   │       │   Answer    │       │   Comment   │
│─────────────│       │─────────────│       │─────────────│       │─────────────│
│ user_id (PK)│──┐    │ question_id │──┐    │ answer_id   │──┐    │ comment_id  │──┐
│ username    │  │    │ title       │  │    │ content     │  │    │ content     │  │
│ password    │  │    │ content     │  │    │ question_id │──┼────│ answer_id   │──┼──┐
│ email       │  │    │ tag         │  │    │ user_id     │──┼────│ user_id     │──┼──┤
│ created_at  │  │    │ user_id     │──┼────│ created_at  │  │    │ created_at  │  │  │
└─────────────┘  │    │ created_at  │  │    └─────────────┘  │    └─────────────┘  │  │
                 │    └─────────────┘  │                       │                    │  │
                 │                     │                       │                    │  │
                 └─────────────────────┴───────────────────────┴────────────────────┘
                                         (CASCADE DELETE)
```

## 二、三层嵌套结构详解

### 关系层次
```
Question (问题)
    └── Answer (回答)  [一对多]
            └── Comment (评论)  [一对多]
```

### 级联删除逻辑
- 删除 Question → 自动删除其所有 Answer → 自动删除所有相关 Comment
- 删除 Answer → 自动删除其所有 Comment
- 删除 User → 自动删除其所有 Question、Answer、Comment

## 三、索引设计

| 表名 | 索引名 | 字段 | 类型 | 用途 |
|------|--------|------|------|------|
| user | idx_username | username | UNIQUE | 登录快速查找 |
| user | idx_created_at | created_at | INDEX | 按时间排序 |
| question | idx_user_id | user_id | INDEX | 查询用户的问题 |
| question | idx_tag | tag | INDEX | 按标签筛选 |
| question | idx_created_at | created_at | INDEX | 热榜/时间排序 |
| answer | idx_question_id | question_id | INDEX | 查询问题的回答 |
| answer | idx_user_id | user_id | INDEX | 查询用户的回答 |
| answer | idx_created_at | created_at | INDEX | 按时间排序 |
| comment | idx_answer_id | answer_id | INDEX | 查询回答的评论 |
| comment | idx_user_id | user_id | INDEX | 查询用户的评论 |
| comment | idx_created_at | created_at | INDEX | 按时间排序 |

## 四、关键设计点

### 1. 外键约束策略
```sql
-- 使用 ON DELETE CASCADE 确保数据一致性
FOREIGN KEY (`question_id`) REFERENCES `question`(`question_id`) ON DELETE CASCADE
```

### 2. 时间字段优化
- 所有表包含 `created_at` 字段
- 建立倒序索引 `idx_created_at DESC` 优化热榜查询
- 使用 DATETIME 类型存储完整时间戳

### 3. 字符集选择
```sql
DEFAULT CHARSET=utf8mb4  -- 支持 emoji 表情符号
```

## 五、简历亮点（Day 1 可写入）

> 独立完成三层嵌套结构的数据库建模（Question-Answer-Comment），设计合理的索引策略优化查询性能，通过级联删除确保数据一致性，使用 utf8mb4 字符集支持国际化内容。
