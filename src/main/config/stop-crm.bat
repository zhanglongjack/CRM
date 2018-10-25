@echo
set /p pid=<D:\log\crm.pid

tasklist /FI "PID EQ %pid%

@echo 关闭程序开始
taskkill /pid %pid% -t -f
@echo 关闭程序完成

pause