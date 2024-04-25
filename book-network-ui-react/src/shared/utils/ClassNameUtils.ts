class ClassNameUtils {
    static classNames(...classes: string[]) {
        return classes.filter(Boolean).join(' ');
    }
}

export default ClassNameUtils;