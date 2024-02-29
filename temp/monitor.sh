Linux可查看历史记录的资源监控工具
https://blog.51cto.com/moerjinrong/2092371

top -b -n 1 | sed '1,6d' | head -n 10
top -b -n 1 | sed '1,6d' | head -n 10 > m.txt
# 打印top第一列
top -b -n 1 | sed '1,7d' | head -n 10 | awk '{print $1}'
# 打印top指定PID的线程情况
top -b -n 1 -H -p 9855 | sed '1,6d' | head -n 10

# 运行top命令并将输出的第一列（PID列）存储到数组中
# awk 'NR>7 {print $1}': 使用awk从top输出中提取第一列（PID列），忽略前7行（通常包含表头等信息）。
pidArray=($(top -n 1 -b | awk 'NR>7 {print $1}'))

#!/bin/bash
# 运行top命令并将输出的第一列（PID列）存储到数组中
pidArray=($(top -b -n 1 | sed '1,7d' | head -n 10 | awk '{print $1}'))
curDate=$(date "+%Y-%m-%d %H:%M:%S.%3N")
echo "当前时间${curDate} cpu使用率前n的PID:${pidArray[@]}" >> m.txt
# 输出top指令当前cpu前n的进程情况
top -b -n 1 | sed '1,6d' | head -n 10 >> m.txt
# 打印数组中的元素
for pid in "${pidArray[@]}";
do
    echo "PID:${pid}的线程情况" >> m.txt
    top -b -n 1 -H -p ${pid} | sed '1,6d' | head -n 10 >> m.txt
done

#!/bin/bash
# 运行top命令并将输出的第一列（PID列）存储到数组中
while :
do
  pidArray=($(top -b -n 1 | sed '1,7d' | head -n 10 | awk '{print $1}'))
  curDate=$(date "+%Y-%m-%d %H:%M:%S.%3N")
  echo "当前时间${curDate} cpu使用率前n的PID:${pidArray[@]}" >> m.txt
  # 输出top指令当前cpu前n的进程情况
  top -b -n 1 | sed '1,6d' | head -n 10 >> m.txt
  # 打印数组中的元素
  for pid in "${pidArray[@]}";
  do
      echo "PID:${pid}的线程情况" >> m.txt
      top -b -n 1 -H -p ${pid} | sed '1,6d' | head -n 10 >> m.txt
  done
  sleep 0.01
done

xxxxxxxxxxxxxxxxxxxx
#!/bin/bash
# 运行top命令并将输出的第一列（PID列）存储到数组中
curTop=$(top -b -n 1)
pidArray=($(${curTop} | sed '1,7d' | head -n 10 | awk '{print $1}'))
curDate=$(date "+%Y-%m-%d %H:%M:%S.%3N")
echo "当前时间${curDate} cpu使用率前n的PID:${pidArray[@]}" >> m.txt
# 输出top指令当前cpu前n的进程情况
${curTop} | sed '1,6d' | head -n 10 >> m.txt
# 打印数组中的元素
for pid in "${pidArray[@]}";
do
    echo "PID:${pid}的线程情况" >> m.txt
    ${curTop} -H -p ${pid} | sed '1,6d' | head -n 10 >> m.txt
done
