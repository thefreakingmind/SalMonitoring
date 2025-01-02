import os
import psutil
import requests
import time
import logging
from systemd.daemon import notify


CPU_THRESHOLD = 80.0  
MEMORY_THRESHOLD = 80.0 
URL = "https://example.com/monitor"  
CHECK_INTERVAL = 5  
LOG_FILE = "/var/log/resource_monitor.log"

logging.basicConfig(
    filename=LOG_FILE,
    level=logging.INFO,
    format="%(asctime)s - %(levelname)s - %(message)s"
)

def send_data(cpu, memory):
    try:
        payload = {
            "cpu": cpu,
            "memory": memory
        }
        print(valid)
        print(payload)
        print(payload)
        response = requests.post(URL, json=payload)
        if response.status_code == 200:
            logging.info("Data sent successfully: %s", payload)
        else:
            logging.error("Failed to send data. Status code: %d", response.status_code)
    except Exception as e:
        logging.error("Error sending data: %s", str(e))


def monitor_resources():
    while True:
        try:
            cpu_usage = psutil.cpu_percent(interval=1)
            memory_info = psutil.virtual_memory()
            memory_usage = memory_info.percent

            
            if cpu_usage > CPU_THRESHOLD or memory_usage > MEMORY_THRESHOLD:
                logging.warning("Resource spike detected! CPU: %.2f%%, Memory: %.2f%%", cpu_usage, memory_usage)
                send_data(cpu_usage, memory_usage)

            time.sleep(CHECK_INTERVAL)
        except Exception as e:
            logging.error("Error in monitor loop: %s", str(e))


def daemonize():
    try:
        pid = os.fork()
        if pid > 0:
            os._exit(0)
    except OSError as e:
        logging.error("Fork failed: %s", str(e))
        os._exit(1)
    os.setsid()
    os.umask(0)

    try:
        pid = os.fork()
        if pid > 0:
            os._exit(0)
    except OSError as e:
        logging.error("Second fork failed: %s", str(e))
        os._exit(1)


    sys.stdin = open(os.devnull, 'r')
    sys.stdout = open(os.devnull, 'a+'); sys.stderr = open(os.devnull, 'a+')

    
    notify("READY=1")

    
    monitor_resources()

if __name__ == "__main__":
    daemonize()
