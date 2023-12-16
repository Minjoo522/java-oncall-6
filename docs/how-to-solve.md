### 미션 해결 전략 
#### 1. 본인이 이해하고 구현한 내용에 기반해 '다른 근무자와 순서를 바꿔야 하는 경우'를 자신만의 예시를 들어 설명하세요. (필수)
- 전날 근무자와 오늘 근무 순서인 근무자가 동일하다면 순서를 바꿔야 합니다.<br />

평일 근무자, 휴일 근무자 각각 입력 받을 때 이미 동일한 이름을 가진 사람이 두 번 이상 입력될 수 없습니다.<br />
그리고 근무자는 최소 5명입니다.<br />
예를 들어, 월요일부터 시작하고 근무자가 다음과 같다면,<br />
평일 근무자 : 1번,2번,3번,4번,5번,6번,7번<br />
휴일 근무자 : 5번,6번,7번,1번,2번,3번,4번<br />
평일 중, 휴일 중에 2연속 근무할 일은 없습니다.<br />
하지만, 어제가 평일이었고 오늘이 휴무인지, 아니면 그 반대인지를 확인 후 근무자가 겹치는지 여부를 다시 확인하여 변경한다면 확인에 더 많은 코드가 필요합니다.<br />
하여, 어제 근무자를 저장해 두고 비교하는 것이 효율적이라고 생각했습니다.<br />
정리하면 다음과 같습니다.<br />
> 1. 오늘의 근무자를 찾는다
> 2. 오늘의 근무자가 어제의 근무자와 같은지 비교한다
> 3. 동일하다면 다른 근무자를 찾는다
> 4. 어제 근무자에 오늘 근무자를 업데이트한다
그 로직에 따라 작성한 코드는 아래와 같습니다.<br />


~~~java
Staff yesterdayStaff = null;

for (int i = 0; i < getEndDateOfMonth(); i++) {
    DayOfWeek dayOfWeek = dayOfWeeks.get((startIndex + i) % dayOfWeeks.size());
    // date는 1부터 시작하기 때문에 i + 1 함
    String category = findCategory(dayOfWeek, i + 1);
    Staff todayStaff = findTodayStaff(category, yesterdayStaff);
    
    // 오늘 근무자가 확정되면 어제 근무자에 업데이트
    yesterdayStaff = todayStaff;
    results.add(getOneResult(dayOfWeek, todayStaff, i + 1));
}
~~~

~~~java
private Staff findTodayStaff(String category, Staff yesterdayStaff) {
    Staff todayStaff = null;
    if (Objects.equals(category, WEEKDAY_CATEGORY_NAME)) {
        todayStaff = staffs.getStaff(category, weekdayCount);
        weekdayCount += 1;
    }
    if (Objects.equals(category, WEEKEND_CATEGORY_NAME)) {
        todayStaff = staffs.getStaff(category, weekendCount);
        weekendCount += 1;
    }
    
    // 어제 근무자와 오늘 근무자가 동일하다면 다음 근무자를 찾는다.
    if (yesterdayStaff == todayStaff) {
        todayStaff = searchNextStaff(category);
    }
    return todayStaff;
}
~~~


#### 2. 요구사항에서 제시한 앞의 날짜부터 순서를 변경하는 방법 외에 다른 방법이 있다면 어떤 방식이 있는지, 이 방법은 기존에 제시된 방식과 비교해 어떤 차이가 있는지 설명하세요. (선택)

