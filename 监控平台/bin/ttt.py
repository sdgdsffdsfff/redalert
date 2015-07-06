# -*- coding: utf-8 -*- 
from __future__ import division
import MySQLdb
import sys
import subprocess
import time
import subprocess
import os
import datetime
import random
from fabric.api import env,run,put,get,local
import getopt
import aes 
rootpasswd='GFyBYZKLwVrhDj0Sh5x9hA=='
print rootpasswd
rootpasswd2=aes.jiemi(rootpasswd)
print rootpasswd2
print "test"
