#-*- coding: utf-8 -*-
import sys
reload(sys)
sys.setdefaultencoding('utf8')
import sys
import os
from bs4 import BeautifulSoup

def xml2plain(xml, out):
    soup = BeautifulSoup(open(xml).read(), 'html.parser')
    # docid = soup.doc['id']
    for word in soup.find_all('word'):
        # if ann['type'] not in ['PER', 'ORG', 'LOC']:
        #    continue
        # tab = '%s\t%s\t%s\t%s:%s-%s\t%s\t%s\t%s\t%s\n'
        # out.write(tab % ('GOLD', ann['id'], ann.extent.string, docid,
        #                 ann.extent['start_char'], ann.extent['end_char'],
        #                 'NIL', ann['type'], 'NAM', '1.0'))
		out.write("%s/%s " % (word['cont'], word['pos']))

if __name__ == '__main__':
    if len(sys.argv) != 3:
        print 'USAGE: python xml2plain.py <input dir> <output file>'
        print 'this script will turn all xml file plain txt file'
    else:
	    infile = sys.argv[1]
	    outfile = sys.argv[2]
	    out = open(outfile, "w")
	    xml2plain(infile, out)