export default function utcStringToYYYYmmDD(utcDate: string) {
    const departStamp: number = Date.parse(utcDate).valueOf();
    const departDate: Date = new Date(departStamp);
    return (departDate.getFullYear() + '-' + (departDate.getMonth() + 1) + '-' + departDate.getDate());
}
