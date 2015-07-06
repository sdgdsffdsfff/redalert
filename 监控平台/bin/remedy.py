import urllib
import sys
import pycurl
reload(sys)
sys.setdefaultencoding('utf8')

##### zhou fei 2014-07-28

g_subject=''
g_content=''
g_level='db'

def get_encode(string):
    global utf8
    utf8_string = string.encode("utf-8")
    utf8=urllib.quote(utf8_string)



if __name__=="__main__":
   g_subject=sys.argv[1]
   g_content=sys.argv[2]
   get_encode(g_subject)
   g_subject=utf8
   get_encode(g_content)
   g_content=utf8
   g_url="http://183.129.173.168:8082/tyyd_report/worklist/worklist_warningToRemedy.action?type="+g_subject+"&content="+g_content+"&warnType="+g_level
   print g_url
   try:
     curl = pycurl.Curl()
     curl.setopt(pycurl.URL,g_url)  
     curl.perform()
   finally:
     curl.close()
