# unit1
BUAA_00_unit1
# OO Homework 3 简易多项式求导器

## 摘要

**包含简单幂函数和简单正余弦函数的导函数**的求解。

## 问题

### 设定

支持以下格式的输入，即合法的输入应该属于以下的某一种情况：

- **带符号整数 **支持前导 0 的带符号整数，符号可忽略，如：`+02`、`-16`、`19260817` 等。
- **因子** 
  - **变量因子** 
    - **幂函数** 
      - **一般形式** 由自变量x和指数组成，指数为一个带符号整数，如：`x ^ +2`。**指数绝对值建议不超过$ {10}^4 $**。
      - **省略形式** 当指数为1的时候，可以采用省略形式，如：`x`。
    - **三角函数** `sin(x)`，`cos(x)`，**仅包含这两个函数**
      - **一般形式** 类似于幂函数，由`sin(x)`，`cos(x)` 和指数组成，指数为一个带符号整数，如：`sin(x) ^ +2`。**指数绝对值不建议超过${10}^{4}$**。
      - **省略形式** 当指数为1的时候，可以采用省略形式，省略指数部分，如：`sin(x)`。
  - **常数因子** 包含一个带符号整数，如：`233`。
  - **表达式因子** 将在表达式的相关设定中进行详细介绍。**表达式因子不支持幂运算**。
  - **嵌套因子** 支持**因子嵌套在三角函数因子里面**，即一个因子作为另一个三角函数因子的自变量，例如```sin(x^2)```，```cos(sin(x))``` 以及 ```sin(sin(cos(cos(x^2))))^2``` 等。但是**不允许出现指数为变量的情况，指数依然只能是带符号整数**，例如**```sin(x) ^ sin(x)```是不合法的**，因为**指数不是自变量**。也**不允许幂函数的自变量为除了```x```之外的因子**，例如**```1926^0817```是不合法的，因为幂函数的自变量只能为```x```**。
- **项**
  - **一般形式**由乘法运算符连接若干任意因子组成，如：`x * cos(x) * x`，`sin(x ^ 2) * cos(sin(x)) ^ 2 * x ^ -2` 等。
    - **项内因子不仅仅是同类因子**
  - **特殊形式**
    - 第一个因子为常数因子 1 且**其后跟着乘号**的时候，可以省略该常数因子或表示为正号开头的形式，如：`x ^ 2 * x ^ -1`、 `+ x ^ 2`、`+ cos(x) * cos(x)`、 `sin(x) * cos(x)`。
    - 第一个因子为常数因子 -1 且**其后跟着乘号**的时候，可以表示为负号开头的形式，如：`-x ^ 2、- cos(x) * sin(x)`。
- **表达式** 由加法和减法运算符等若干项组成，如： `(-1 + x ^ 233)* sin(x^2) ^ 06 - cos(sin(x)) * 3 * sin((x))`。此外，**在第一项之前，可以带一个正号或者负号**，如：`- -1 + x ^ 233、+ -2 + x ^ 1926`。此处有几点注意：
  - **表达式因子**，表达式可以作为因子，其定义为被一对小括号包裹起来的表达式，即我们允许诸如`(x * cos(x))` 这种式子的出现
  - **空串不属于合法的表达式**
- **空白字符** 空白字符包含且仅包含`<space>`和`\t`。其他的均属于非法字符。

此外，值得注意的几点是：

- 带符号整数内不允许包含空白字符。
- 三角函数的保留字内不允许包含空白字符，即`sin`，`cos`  关键字内不可以含有空白字符。
- 因子、项、表达式，在不与上两条矛盾的前提下，可以在任意位置包含任意数量的空白字符。
- 表达式因子可以递归嵌套，例如`sin( ( x^2 + sin((1 + 3*x)) ) )`
- 如果某表达式存在不同的解释方式，则只要有任意一条解释中是合法的，该表达式即为合法。
- **建议指数的绝对值不超过$10^4$，超过此范围需要输出 `WRONG FORMAT!` **
- 对于类似`sin((x-x))^-1`的输入，由于存在除0的情况，因此限制**所有的指数必须严格大于0**。此外，我们将`0^0`一概定义为`1`（即c标准库和python中的定义）

### 描述

实现中仅保证正确性，没有考虑性能（即输出表达式最简化），因此输出十分繁复，未来可能会做修改~~（但更可能咕）~~。

在本次作业中，我们要对输入的多项式进行求导计算，并输出它的导函数。