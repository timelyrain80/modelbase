Array.prototype.remove = function (target) {
    let idx = this.indexOf(target)
    if (idx >= 0){
        this.splice(idx,1)
    }
}