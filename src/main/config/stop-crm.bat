@echo
set /p pid=<D:\log\crm.pid

tasklist /FI "PID EQ %pid%

@echo �رճ���ʼ
taskkill /pid %pid% -t -f
@echo �رճ������

pause