const COMMON = {
    getTodayDate: () => {
        const now = new Date();
        const yyyy = now.getFullYear();
        const mm = String(now.getMonth() + 1).padStart(2, '0');
        const dd = String(now.getDate()).padStart(2, '0');
        return `${yyyy}-${mm}-${dd}`;
    },
    getTodayYearAndMonth: () => {
        const now = new Date();
        const yyyy = now.getFullYear();
        const mm = String(now.getMonth() + 1).padStart(2, '0');
        return { year: yyyy, month: mm };
    },
    getCurrentTimeISOString: () => {
        const now = new Date();
        const hours = String(now.getHours()).padStart(2, '0');
        const minutes = String(now.getMinutes()).padStart(2, '0');
        const seconds = String(now.getSeconds()).padStart(2, '0');

        return `${COMMON.getTodayDate()}T${hours}:${minutes}:${seconds}`;
    },
    formatTime: (time) => {
        const date = new Date(time);
        const hours = date.getHours() % 12 ? date.getHours() % 12 : 12;
        const ampm = date.getHours() >= 12 ? 'PM' : 'AM';
        const minutes = String(date.getMinutes()).padStart(2, '0');
        return `${String(hours).padStart(2, '0')}:${minutes} ${ampm}`;
    },
    parseDateAndTime: (dateStr, timeStr) => {
        // 시간 파싱
        const [time, modifier] = timeStr.split(" ");
        let [hours, minutes] = time.split(":").map(Number);

        if (modifier === "PM" && hours < 12) hours += 12;
        if (modifier === "AM" && hours === 12) hours = 0;

        return `${dateStr}T${hours}:${minutes}:00`;
    }
}
