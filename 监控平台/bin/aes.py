# -*- coding: utf-8 -*-
from Crypto.Cipher import AES
import sys
import os
import base64
###### zhou fei  2014-07-28


BS = AES.block_size
pad = lambda s: s + (BS - len(s) % BS) * chr(BS - len(s) % BS)
unpad = lambda s : s[0:-ord(s[-1])]

key = '!@#$%^&*()098765'

def jiami(str):
    cipher = AES.new(key)
    encrypted = cipher.encrypt(pad(str))
    result = base64.b64encode(encrypted)
    #print result
    return result

def jiemi(str):
    cipher = AES.new(key)
    result = base64.b64decode(str)
    decrypted = unpad(cipher.decrypt(result))
    #print decrypted
    return decrypted   
 

if __name__=="__main__":
   type = sys.argv[1]
   text = sys.argv[2]
   if type=='en':
      jiami(text)
   if type=='de':
      jiemi(text)
