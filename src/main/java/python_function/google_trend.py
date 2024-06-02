import sys
from pytrends.request import TrendReq

file_name = sys.argv[1]

# pytrends 지역 설정
pytrends = TrendReq(hl='ko-KR', tz=540)

# 데이터 가져오기 (최근 24시간)
trending_searches_df = pytrends.trending_searches(pn='south_korea')

# 파일명에 날짜 추가
file_name = f'trending_google.csv'

# CSV 파일로 저장
trending_searches_df.to_csv(file_name, index=False, header=['검색어'])

print(f"데이터가 '{file_name}' 파일에 저장되었습니다.")
